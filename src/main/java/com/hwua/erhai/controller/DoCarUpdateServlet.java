package com.hwua.erhai.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.servlet.ICarService;
import com.hwua.erhai.servlet.impl.MockCarService;
import com.hwua.erhai.servlet.query.QueryCondition;
import com.hwua.erhai.vo.DoCarUpdateUsableResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DoCarUpdateServlet", value = "/doCarUpdate")
public class DoCarUpdateServlet extends HttpServlet {
    ICarService CarService=new MockCarService();
    //上传文件储存目录
    private static final String UpLOAD_DIRECTORY="upload";
    //上传配置
    private static final  int MEMORY_THRESHOLD=1024*1024*3;//3mb
    private static final int MAX_FILE_SIZE=1024*1024*40;
    private static final int MAX_REQUEST_SIZE=1024*1024*50;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ServletFileUpload.isMultipartContent(request)){
            //如果不是则停止
            PrintWriter writer=response.getWriter();
            writer.println("Error:表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }
        //配置上传参数
        DiskFileItemFactory factory=new DiskFileItemFactory();
        //设置内存临界值，超过后将产生临时文件并储存于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        //设置临时储存目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload=new ServletFileUpload(factory);
        //设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
        //设置最大请求值（包含文件和表单数据）
        upload.setFileSizeMax(MAX_REQUEST_SIZE);
        //中文处理
        upload.setHeaderEncoding("UTF-8");
        //构造临时路径来存储上传的文件
        //这个路径相对当前应用的目录
        String uploadPath=request.getServletContext().getRealPath("./")+File.separator+UpLOAD_DIRECTORY;
        //如果目录不存在则创建
        File uploadDir=new File(uploadPath);
        if (!uploadDir.exists()){
            boolean succeed=uploadDir.mkdir();
            if (!succeed){
                //如果创建上传目录失败，返回错误
                PrintWriter writer=response.getWriter();
                writer.println("Error:创建目录失败");
                writer.flush();
                return;
            }
        }
        Map<String, FileItem> fileItemMap=new HashMap<>();
        HttpSession session=request.getSession();
        try {
            List<FileItem> formItems=upload.parseRequest(request);
            if (formItems !=null && formItems.size()>0){
//迭代表单数据
                for (FileItem item:formItems){
                    fileItemMap.put(item.getFieldName(),item);
                }
            }
            String carId=fileItemMap.get("carId").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(carId),"汽车编号不能为空");
            String brand =fileItemMap.get("brand").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(brand),"品牌不能为空");

            String category =fileItemMap.get("category").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(category),"类型不能为空");

            String model =fileItemMap.get("model").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(model),"型号不能为空");

            String carNumber =fileItemMap.get("carNumber").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(carNumber),"车牌号不能为空");
            String comments =fileItemMap.get("comments").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(comments),"简介不能为空");
            String color =fileItemMap.get("color").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(color),"颜色不能为空");

            String price=fileItemMap.get("price").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(price),"价格不能为空");
            String rent =fileItemMap.get("rent").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(rent),"租金不能为空");

            String usable
                    =fileItemMap.get("usable")==null?"":
                    fileItemMap.get("usable").getString("UTF-8");
            List<QueryCondition>queryConditionList=new ArrayList<>() ;
            queryConditionList.add(new QueryCondition("carId",carId));
            List<Car> carList= CarService.queryCars(queryConditionList,1,0);
            if (carList==null||carList.size()==0){
                request.setAttribute("message","找不到该汽车");
                request.getRequestDispatcher("/carFoundError.jsp").forward(request,response);
                return;
            }
            if (carList.size()>1){
                request.setAttribute("message","找到多辆汽车");
                request.getRequestDispatcher("/carFoundError.jsp").forward(request,response);
                return;
            }
            Car car = new Car();
            car.setId(-1);
            car.setCarNumber(carNumber);
            car.setBrandId(-1);
            car.setBrandName(brand);
            car.setModel(model);
            car.setColor(color);
            car.setCategoryId(-1);
            car.setCategoryName(category);
            car.setComments(comments);
            car.setPrice(Double.parseDouble(price));
            car.setRent(Double.parseDouble(rent));
            car.setStatus(0);
            car.setUsable("on".equals(usable)?0:1);
            Car newCar= CarService.addAndReturnCar(car);
            if (newCar==null){
                throw new Exception("新增汽车失败");
            }else{
                FileItem imageItem=fileItemMap.get("image");
                if (imageItem.getSize()>0){
                    long id= newCar.getId();
                    String filename=String.format("car_%d.img",id);
                    String filePath=uploadPath+File.separator+filename;
                    File storeFile=new File(filePath);
                    //在控制台输入文件的上传路径
                    System.out.println(filePath);
                    //保存文件到硬盘
                    imageItem.write(storeFile);
                }
            }
            session.setAttribute("result","succeed");
            session.setAttribute("message","修改汽车成功");
        } catch (Exception ex) {
            session.setAttribute("result","failed");
            session.setAttribute("message","修改汽车失败，错误信息："+ex.getMessage());
        }
        //跳转到message.jsp
//        request.getServletContext().getRequestDispatcher("/doCarAdd.jsp").forward(request,response);
//response.sendRedirect(String.format("doCarAdd.jsp?result=%s",result));
        response.sendRedirect("doCarUpdate.jsp");
    }

}

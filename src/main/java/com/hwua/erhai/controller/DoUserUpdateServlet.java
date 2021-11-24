package com.hwua.erhai.controller;

import com.google.common.base.Preconditions;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.servlet.ICarService;
import com.hwua.erhai.servlet.IUserService;
import com.hwua.erhai.servlet.impl.MockCarService;
import com.hwua.erhai.servlet.impl.MockUserService;
import com.hwua.erhai.servlet.query.QueryCondition;
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

@WebServlet(name = "DoUserUpdateServlet", value = "/doUserUpdate")
public class DoUserUpdateServlet extends HttpServlet {
    IUserService iUserService=new MockUserService();
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
            String userId=fileItemMap.get("userId").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(userId),"用户id不能为空");
            String userName =fileItemMap.get("username").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(userName),"用户名不能为空");

            String password =fileItemMap.get("password").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(password),"密码不能为空");

            String sex =fileItemMap.get("sex").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(sex),"性别不能为空");

            String idNumber =fileItemMap.get("idNumber").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(idNumber),"身份证号不能为空");
            String tel =fileItemMap.get("tel").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(tel),"电话不能为空");
            String addr =fileItemMap.get("addr").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(addr),"地址不能为空");

            String type
                    =fileItemMap.get("type")==null?"":
                    fileItemMap.get("type").getString("UTF-8");
            List<QueryCondition>queryConditionList=new ArrayList<>() ;
            queryConditionList.add(new QueryCondition("userId",userId));
            List<User> userList= iUserService.queryUser(queryConditionList,1,0);
            if (userList==null||userList.size()==0){
                request.setAttribute("message","找不到该用户");
                request.getRequestDispatcher("/userFoundError.jsp").forward(request,response);
                return;
            }
            if (userList.size()>1){
                request.setAttribute("message","找到多个用户");
                request.getRequestDispatcher("/userFoundError.jsp").forward(request,response);
                return;
            }
           User user=userList.get(0);

            user.setUserName(userName);
            user.setPassword(password);
            user.setSex(Integer.parseInt(sex));
            user.setTel(tel);
            user.setIdNumber(idNumber);
            user.setAddr(addr);
            user.setType("管理员".equals(type)?1:0);


            User newUser= iUserService.updateAndReturnUser(user);
            if (newUser==null){
                throw new Exception("修改用户失败");
            }else{
                FileItem imageItem=fileItemMap.get("image");
                if (imageItem.getSize()>0){
                    long id= newUser.getId();
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
            session.setAttribute("message","修改用户成功");
        } catch (Exception ex) {
            session.setAttribute("result","failed");
            session.setAttribute("message","修改用户失败，错误信息："+ex.getMessage());
        }
        //跳转到message.jsp
//        request.getServletContext().getRequestDispatcher("/doCarAdd.jsp").forward(request,response);
//response.sendRedirect(String.format("doCarAdd.jsp?result=%s",result));
        response.sendRedirect("doUserUpdate.jsp");
    }
}

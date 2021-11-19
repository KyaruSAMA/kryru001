package com.hwua.erhai.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler {
    void handleRs(ResultSet rs) throws SQLException;
}

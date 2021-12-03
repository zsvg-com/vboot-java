package com.zsvg.vboot.common.mvc.dao;

import java.sql.SQLException;

public abstract  class BaseSqler {

    public abstract  String getSql() throws SQLException;


    public abstract  Object[] getParams();

}

package org.dromara.testhub.plugins.http.actions.model;

import com.goddess.nsrule.core.executer.mode.base.action.Param;
import lombok.Data;

import java.util.List;

@Data
public class HttpModel {
    private String baseUrl;
    private String url;
    private String method;
    private int timeout;

    private List<Param> headers;
    private Body body;
    private List<Param> params;
    private List<Param> rests;

}

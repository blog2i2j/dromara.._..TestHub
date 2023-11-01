package org.dromara.testhub.plugins.compare.actions.model;

import com.goddess.nsrule.core.executer.mode.base.action.Execute;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

import java.util.List;

public class TestHubExecuteCheckObj extends TestHubExecute {
    private List<CheckObj> checkObjs;

    public List<CheckObj> getCheckObjs() {
        return checkObjs;
    }

    public void setCheckObjs(List<CheckObj> checkObjs) {
        this.checkObjs = checkObjs;
    }

    public TestHubExecuteCheckObj(Execute execute) {
        super(execute);
    }
}

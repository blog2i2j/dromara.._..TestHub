package org.dromara.testhub.plugins.sql.actions.model;

import org.dromara.testhub.nsrule.core.executer.mode.base.action.Execute;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

public class TestHubExecuteSql extends TestHubExecute {
    //数据库事务ID
    private String conKey;
    //是否自动提交
    private boolean commit = false;

    public TestHubExecuteSql(Execute execute) {
        super(execute);
    }

    public String getConKey() {
        return conKey;
    }

    public void setConKey(String conKey) {
        this.conKey = conKey;
    }

    public boolean getCommit() {
        return commit;
    }

    public void setCommit(Boolean commit) {
        this.commit = commit;
    }
}

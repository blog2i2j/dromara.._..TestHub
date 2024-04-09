package org.dromara.testhub.plugins.compare.actions;


import org.dromara.testhub.nsrule.core.executer.mode.base.action.RunState;
import org.dromara.testhub.plugins.compare.actions.dto.CheckObjExecuteResult;
import org.dromara.testhub.plugins.compare.actions.model.CheckObj;
import org.dromara.testhub.plugins.compare.actions.model.TestHubExecuteCheckObj;
import org.dromara.testhub.sdk.action.BaseExecuteResultHandler;
import org.dromara.testhub.sdk.action.model.HandlerResult;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

import java.util.HashMap;
import java.util.Map;

public class CheckObjExecuteResultHandler implements BaseExecuteResultHandler {
    @Override
    public HandlerResult handler(TestHubAction action, TestHubExecute execute, RunState.Item stateItem, HashMap<String, Object> flowData, Object executeData) {

        CheckObjExecuteResult checkObjExecuteResult = new CheckObjExecuteResult(action,execute);
        HandlerResult handlerResult = new HandlerResult(checkObjExecuteResult);
        Map<String, Boolean> checkObjResult = new HashMap<>();
        checkObjExecuteResult.setCheckObjResult(checkObjResult);
        if(stateItem.getResult().getException()!=null){
            handlerResult.addErrorNumber();
            handlerResult.setExecFlag(false);
            return handlerResult;
        }



        TestHubExecuteCheckObj executeCheckObj = (TestHubExecuteCheckObj)execute;
        for (CheckObj item : executeCheckObj.getCheckObjs()) {
            Boolean flag = ((Map<String, Boolean>) flowData.get(execute.getCode())).get(item.getCode());
            if (flag == false) {
                handlerResult.addErrorNumber();
                handlerResult.setExecFlag(false);
            }
            checkObjResult.put(item.getCode(),flag);
        }
        return handlerResult;
    }
}

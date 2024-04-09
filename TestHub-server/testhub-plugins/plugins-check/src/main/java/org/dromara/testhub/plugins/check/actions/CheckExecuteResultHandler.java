package org.dromara.testhub.plugins.check.actions;

import org.dromara.testhub.nsrule.core.executer.mode.base.action.RunState;
import org.dromara.testhub.nsrule.core.executer.mode.ruleLine.RuleLine;
import org.dromara.testhub.plugins.check.actions.model.CheckItem;
import org.dromara.testhub.plugins.check.actions.model.TestHubExecuteCheck;
import org.dromara.testhub.plugins.check.actions.dto.CheckExecuteResult;
import org.dromara.testhub.plugins.check.actions.dto.CheckResultDto;
import org.dromara.testhub.sdk.action.BaseExecuteResultHandler;
import org.dromara.testhub.sdk.action.model.HandlerResult;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckExecuteResultHandler implements BaseExecuteResultHandler {
    @Override
    public HandlerResult handler(TestHubAction action, TestHubExecute execute, RunState.Item stateItem, HashMap<String, Object> flowData, Object executeData) {

        CheckExecuteResult executeResult = new CheckExecuteResult(action, execute);
        HandlerResult handlerResult = new HandlerResult(executeResult);

        if(stateItem.getResult().getException()!=null){
            handlerResult.addErrorNumber();
            handlerResult.setExecFlag(false);
            return handlerResult;
        }

        for (RuleLine<CheckItem> item : ((TestHubExecuteCheck) execute).getRuleLines()) {
            CheckItem checkItem = item.getResult();
            CheckResultDto checkResultDto = ((Map<String, CheckResultDto>)flowData.get(execute.getCode())).get(checkItem.getCode());

            if(checkResultDto!=null){
                List<Boolean> flags = checkResultDto.getItemFlags();
                for (Boolean flag : flags) {
                    if (!flag) {
                        handlerResult.setExecFlag(false);
                        handlerResult.addErrorNumber();
                    }
                }
            }else {
                handlerResult.setExecFlag(false);
                handlerResult.addErrorNumber();
            }

        }
        return handlerResult;
    }
}

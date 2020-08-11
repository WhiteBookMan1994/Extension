package com.cmt.extension.core.config;

import static com.cmt.extension.core.BusinessContext.BIZ_CODE_KEY;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.cmt.extension.core.BusinessContext;

import lombok.extern.slf4j.Slf4j;

/**
 * Dubbo远程调用获取bizcode参数
 *
 * @author yonghuang
 */
@Activate(group = {Constants.PROVIDER})
@Slf4j
public class ProviderBizCodeFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String bizCode = RpcContext.getContext().getAttachment(BIZ_CODE_KEY);
        log.debug("bizCode:={}", bizCode);
        BusinessContext.setBizCode(bizCode);

        return invoker.invoke(invocation);
    }
}

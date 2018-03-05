package com.wxhao.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @ClassName: LogAspect
 * @Description: 日志切面
 * <p>打印接口入参和出参，以及接口耗时</p>
 * @version V1.0
 */
@Component
@Aspect
public class LogAspect {

	private static Logger log = Logger.getLogger(LogAspect.class);
	
	
	@Pointcut(
			"execution(* com.wxhao.controller.*.*(..))"
			)
	public void runTimeStatistic(){
		
	}
	
	@Around("runTimeStatistic()")
	public Object doAround(ProceedingJoinPoint joinPoint){	
		long start = System.currentTimeMillis();
		Object resultObj = null;
		try {
			//打印方法签名
			log.info(joinPoint.getSignature());
			//参数
			//Object[] args = joinPoint.getArgs();
			//log.info("args="+args[0]);
			//返回结果
			resultObj = joinPoint.proceed();
			//打印返回结果
			//log.info("result="+resultObj);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
			resultObj = null;
		}
		long end = System.currentTimeMillis();
		log.info("接口响应完成,耗时="+(end-start));
		return resultObj;
	}
}

package xcu.lxj.ssmchat.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import xcu.lxj.ssmchat.utils.ResponseBadyData;



@Aspect
@Component
public class ControllerAspect {

    @Around("execution(* xcu.lxj.ssmchat.controller..*(..))")
    public ResponseBadyData  afterThrowingAdvice(ProceedingJoinPoint joinPoint) {
        ResponseBadyData proceed =null;
        try {
             proceed = (ResponseBadyData)joinPoint.proceed();
        } catch (Throwable e) {
            proceed =ResponseBadyData.failure(null);
        }
        return proceed;
    }

}

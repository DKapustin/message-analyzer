package liga.medical.messageanalyzer.core.aop;

import liga.medical.dto.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import liga.medical.dto.MessageDto;
import liga.medical.dto.LogType;

import java.util.Arrays;


@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class LoggingAdvice {

    private void logMessage(String className, String methodName, Object[] args) {
        log.info(" Class: " + className + ", method: " + methodName + ", args: " + Arrays.toString(args));
    }
    @Pointcut(value = "execution(* liga.medical.messageanalyzer.core.controller.*.*(..))")
    public void dbLog() {
    }

    @Around("dbLog()")
    public Object dbLogger(ProceedingJoinPoint pjp) {
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();

        Object obj = null;
        MessageType messageType = null;
        try {
            obj = pjp.proceed();
            messageType = ((MessageDto)args[0]).getMessageType();
        } catch (Throwable throwable) {
            logMessage(LogType.EXCEPTION, className, methodName, args);
            throwable.printStackTrace();
        }

        switch (messageType) {
            case ALERT:
            case DAILY:
                logMessage(LogType.DEBUG, className, methodName, args);
                break;
            default:
                logMessage(LogType.EXCEPTION, className, methodName, args);
        }
    }
}

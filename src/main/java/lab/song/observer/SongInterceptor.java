package lab.song.observer;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.UUID;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.security.enterprise.SecurityContext;
import lab.song.entities.Song;
import lab.song.observer.event.SongEvent;
import lombok.extern.java.Log;

/**
 * Observer implementation for adding new profession. It will notify all users about it.
 */
@Log
// @ApplicationScoped
@Interceptor
@SongEvent
@Priority(10)
public class SongInterceptor implements Serializable {
    private SecurityContext securityContext;

    @Inject
    SongInterceptor(SecurityContext c){
        this.securityContext = c;
    }

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception{
        
        Object result = context.proceed();

        Method method = context.getMethod();
        String userName = securityContext.getCallerPrincipal().getName();
        String methodName = method.getName().replace("ForCallerPrincipal","");
        var param = context.getParameters()[0];
        UUID id;
        if (param.getClass().equals(Song.class) ){
            id = ((Song)param).getId();
            }
            else{
                id = (UUID)param;
            }
        log.info( userName+" "+ methodName +" " + id.toString() );
        

        return result;
    }

}

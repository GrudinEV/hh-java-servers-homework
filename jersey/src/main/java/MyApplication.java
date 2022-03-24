import jakarta.ws.rs.core.Application;
import resource.CounterResource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(List.of(CounterResource.class));
    }
}

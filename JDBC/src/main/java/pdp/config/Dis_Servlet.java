package pdp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import pdp.config.WebMvc_Config;

public class Dis_Servlet extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvc_Config.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

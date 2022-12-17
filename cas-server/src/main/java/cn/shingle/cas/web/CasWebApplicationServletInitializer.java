package cn.shingle.cas.web;

import cn.shingle.cas.CasWebApplication;
import cn.shingle.cas.utils.CasEmbeddedContainerUtils;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.Map;

/**
 * This is {@link CasWebApplicationServletInitializer}.
 *
 * @author Misagh Moayyed
 * @since 5.0.0
 */
public class CasWebApplicationServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        final Map<String, Object> properties = CasEmbeddedContainerUtils.getRuntimeProperties(Boolean.FALSE);
        return builder
            .sources(CasWebApplication.class)
            .properties(properties)
            .banner(CasEmbeddedContainerUtils.getCasBannerInstance());
    }
}

package cn.shingle.cas.web;

import lombok.ToString;
import org.apereo.cas.CasEmbeddedValueResolver;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.TaskManagementConfigUtils;

/**
 * This is {@link CasWebApplicationContext}.
 *
 * @author Misagh Moayyed
 * @since 5.1.0
 */
@ToString
public class CasWebApplicationContext extends AnnotationConfigEmbeddedWebApplicationContext {

    /**
     * {@inheritDoc}
     * Reset the value resolver on the inner {@link ScheduledAnnotationBeanPostProcessor}
     * so that we can parse durations. This is due to how {@link org.springframework.scheduling.annotation.SchedulingConfiguration}
     * creates the processor and does not provide a way for one to inject a value resolver.
     */
    @Override
    protected void onRefresh() {
        final ScheduledAnnotationBeanPostProcessor sch =
            (ScheduledAnnotationBeanPostProcessor) getBeanFactory()
                .getBean(TaskManagementConfigUtils.SCHEDULED_ANNOTATION_PROCESSOR_BEAN_NAME, BeanPostProcessor.class);
        sch.setEmbeddedValueResolver(new CasEmbeddedValueResolver(this));
        super.onRefresh();
    }
}

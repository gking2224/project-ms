package me.gking2224.projectms.jms;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import me.gking2224.common.client.jms.CommonMessagingConfiguration;

@Import({CommonMessagingConfiguration.class})
@ComponentScan("me.gking2224.projectms.jms")
public class MessagingConfiguration {
}

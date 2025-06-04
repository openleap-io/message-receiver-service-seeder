package io.openleap.mrs.seeder.util;

import io.openleap.mrs.client.model.*;
import net.datafaker.Faker;

import java.util.List;

public class MessageDataGenerator {
    private static final Faker faker = new Faker();

    public static MessageRequest generateMessageRequest(Channel.ChannelTypeEnum channelType) {

        switch (channelType) {
            case TEAMS:
                return generateTeamsMessageRequest();
            case SLACK:
                return generateSlackMessageRequest();
            case EMAIL:
                return generateEmailMessageRequest();
            default:
                throw new IllegalArgumentException("Unsupported channel type: " + channelType);
        }
    }

    private static MessageRequest generateEmailMessageRequest() {
        EmailChannel channel = new EmailChannel();
        channel.setChannelType((Channel.ChannelTypeEnum.EMAIL));

        Message msg = new CustomMessage();
        msg.setMessageType(Message.MessageTypeEnum.CUSTOM);
        msg.setSubject(faker.internet().emailSubject());
        msg.setBody(faker.joke().knockKnock());
        msg.setAttachments(List.of(getAttachment()));

        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setRecipients(List.of(getRecipient(channel)));
        messageRequest.setMessage(msg);

        return messageRequest;
    }


    private static MessageRequest generateTeamsMessageRequest() {
        TeamsChannel channel = new TeamsChannel();
        channel.setTenantId(faker.idNumber().peselNumber());
        channel.setChannelId(faker.idNumber().peselNumber());
        channel.setChannelType((Channel.ChannelTypeEnum.TEAMS));


        Message msg = new CustomMessage();
        msg.setMessageType(Message.MessageTypeEnum.CUSTOM);
        msg.setSubject(faker.internet().emailSubject());
        msg.setBody(faker.joke().knockKnock());
        msg.setAttachments(List.of(getAttachment()));

        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setRecipients(List.of(getRecipient(channel)));
        messageRequest.setMessage(msg);

        return messageRequest;
    }

    public static MessageRequest generateSlackMessageRequest() {
        SlackChannel channel = new SlackChannel();
        channel.setChannelId(faker.idNumber().peselNumber());
        channel.setChannelName(faker.team().name());
        channel.setChannelType(Channel.ChannelTypeEnum.SLACK);

        Message msg = new CustomMessage();
        msg.setMessageType(Message.MessageTypeEnum.CUSTOM);
        msg.setSubject(faker.internet().emailSubject());
        msg.setBody(faker.lorem().paragraph());

        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setRecipients(List.of(getRecipient(channel)));
        messageRequest.setMessage(msg);

        return messageRequest;
    }

    private static Attachment getAttachment() {
        Attachment attachment = new Attachment();
        attachment.setContentType("image/png");
        attachment.setUrl(faker.internet().url());
        attachment.setName("example.png");
        attachment.setBase64Data(faker.image().base64PNG());
        return attachment;
    }

    private static Recipient getRecipient(EmailChannel channel) {
        Recipient recipient = new Recipient();
        recipient.setChannel(channel);
        recipient.setId(faker.internet().emailAddress());
        return recipient;
    }

    private static Recipient getRecipient(SlackChannel channel) {
        Recipient recipient = new Recipient();
        recipient.setChannel(channel);
        recipient.setId(faker.internet().emailAddress());
        return recipient;
    }

    private static Recipient getRecipient(TeamsChannel channel) {
        Recipient recipient = new Recipient();
        recipient.setChannel(channel);
        recipient.setId(faker.internet().emailAddress());
        return recipient;
    }
}

package io.openleap.mrs.seeder.runner;

import io.openleap.mrs.client.ApiClient;
import io.openleap.mrs.client.api.MessageApiApi;
import io.openleap.mrs.client.model.Channel;
import io.openleap.mrs.seeder.util.MessageDataGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class SeederServiceRunner implements CommandLineRunner {
    ApiClient apiClient;

    public SeederServiceRunner(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public void seedData(int numberOfEmailMessages, int numberOfTeamsMessages, int numberOfSlackMessages) {
        MessageApiApi message = new MessageApiApi(apiClient);

        IntStream.range(0, numberOfEmailMessages)
                .forEach(i -> message.sendMessage(
                        MessageDataGenerator.generateMessageRequest(Channel.ChannelTypeEnum.EMAIL)));

        IntStream.range(0, numberOfTeamsMessages)
                .forEach(i -> message.sendMessage(
                        MessageDataGenerator.generateMessageRequest(Channel.ChannelTypeEnum.TEAMS)));

        IntStream.range(0, numberOfSlackMessages)
                .forEach(i -> message.sendMessage(
                        MessageDataGenerator.generateMessageRequest(Channel.ChannelTypeEnum.SLACK)));

    }

    @Override
    public void run(String... args) {
        seedData(args.length > 0 ? Integer.parseInt(args[0]) : 10,
                args.length > 1 ? Integer.parseInt(args[1]) : 10,
                args.length > 2 ? Integer.parseInt(args[2]) : 10);
    }
}

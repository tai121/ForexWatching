package com.forex;

import com.forex.entities.*;
import com.forex.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ForexWatchingApplicationTests {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private SymbolRepository symbolRepository;
	@Autowired
	private HistoricalDataRepository historicalDataRepository;
	@Autowired
	private PacketRepository packetRepository;
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Test
	void contextLoads() {
//		Role roleUser = new Role();
//		roleUser.setName("USER");
//		roleRepository.save(roleUser);
//
//		Role roleCreater = new Role();
//		roleCreater.setName("CREATER");
//		roleRepository.save(roleCreater);
//
//		Role roleEditor = new Role();
//		roleEditor.setName("EDITOR");
//		roleRepository.save(roleEditor);
//
//		Role roleAdmin = new Role();
//		roleAdmin.setName("ADMIN");
//		roleRepository.save(roleAdmin);
//		Category category1 = new Category();
//		category1.setName("Major");
//		category1.setDescription("Major");
//		categoryRepository.save(category1);
//
//		Category category2 = new Category();
//		category2.setName("Minor");
//		category2.setDescription("Minor");
//		categoryRepository.save(category2);
//
//		Category category3 = new Category();
//		category3.setName("Cross");
//		category3.setDescription("Cross");
//		categoryRepository.save(category3);
//
//		Symbol symbol1 = new Symbol();
//		symbol1.setFirst_currency("GBP");
//		symbol1.setSecond_currency("USD");
//		symbol1.setCategory(categoryRepository.findById(1l).orElse(null));
//		symbolRepository.save(symbol1);
//
//		Symbol symbol2 = new Symbol();
//		symbol2.setFirst_currency("EUR");
//		symbol2.setSecond_currency("USD");
//		symbol2.setCategory(categoryRepository.findById(1l).orElse(null));
//		symbolRepository.save(symbol2);



		User user2 = userRepository.getUserByUsername("user2");
		Packet packet1 = new Packet();
		packet1.setName("Gold");
		packet1.setPrice(100000f);
		packet1.setDescription("Gold");
		packetRepository.save(packet1);
		Packet packet2 = new Packet();
		packet2.setName("Platinum");
		packet2.setPrice(200000f);
		packet2.setDescription("Platinum");
		packetRepository.save(packet2);
		Subscription subscription = new Subscription();
		subscription.setPacket(packet2);
		subscription.setUser(user2);
		subscription.setValue(packet2.getPrice());
		subscriptionRepository.save(subscription);
	}

}

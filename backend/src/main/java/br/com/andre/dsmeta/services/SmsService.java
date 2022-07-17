package br.com.andre.dsmeta.services;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import br.com.andre.dsmeta.entities.Sale;
import br.com.andre.dsmeta.repositories.SaleRepository;

@Service
public class SmsService {

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;

	@Autowired
	private SaleRepository saleRepository;
	
	private String response;
	
	public String sendSms(String text) {

		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, text).create();

		System.out.println(message.getSid());
		
		return message.getSid() + "\n" + text;
	}
	
	
	public String sendSmsWithId(Long saleId) {
		
		Consumer<Sale> consumer = new Consumer<>() {

			@Override
			public void accept(Sale t) {
				String date = t.getDate().getMonthValue()
						+ "/" + t.getDate().getYear();
				String msg = "O vendedor " + t.getSellerName()
					+ " foi destaque em " + date
					+ " com um total de R$ " + String.format("%.2f", t.getAmount());
				response = sendSms(msg);
			}
		};
		
		Optional<Sale> optionalSale = saleRepository.findById(saleId);
		optionalSale.ifPresentOrElse(consumer,
				() -> response = "Nothing found!");
		
		return response;
	}
}

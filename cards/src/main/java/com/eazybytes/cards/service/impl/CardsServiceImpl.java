package com.eazybytes.cards.service.impl;

import com.eazybytes.cards.constant.CardsConstants;
import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.entity.Cards;
import com.eazybytes.cards.exception.CardAlreadyExistsException;
import com.eazybytes.cards.exception.ResourceNotFoundException;
import com.eazybytes.cards.mapper.CardsMapper;
import com.eazybytes.cards.repository.CardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardService{

    CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> cards =cardsRepository.findByMobileNumber(mobileNumber);
        if(cards.isPresent()){
            throw new CardAlreadyExistsException("Card already exists with the given mobile number");
        }

        cardsRepository.save(createNewCard(mobileNumber));
    }

    public Cards createNewCard(String mobileNumber){

        Cards newCard = new Cards();
        long cardNumber = 10000000L + new Random().nextInt(90000000);
        newCard.setCardNumber(cardNumber);
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {

      Optional<Cards> cards= Optional.of(cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
              () -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber)));

        return CardsMapper.mapToCardsDto(cards.get(),new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards=cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Cards","cardsNumber", cardsDto.getCardNumber().toString()));
        CardsMapper.mapToCards(cardsDto,cards);
        cardsRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {

        Optional<Cards> cards= Optional.of(cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber)));
        cardsRepository.deleteById(cards.get().getCardId());
        return true;
    }
}

package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.constants.LoansConstants;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.exception.LoanAlreadyExistsException;
import com.eazybytes.loans.exception.ResourceNotFoundException;
import com.eazybytes.loans.mapper.LoansMapper;
import com.eazybytes.loans.repository.LoansRepository;
import com.eazybytes.loans.service.ILoansService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoansServiceImpl implements ILoansService {

    LoansRepository loansRepository;
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loans=loansRepository.findByMobileNumber(mobileNumber);
        if(loans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already exists with the given mobile number");
        }

        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber){
        Loans loans = new Loans();
        long loanNumber = 1000000000L +new Random().nextLong(9000000000L);
        loans.setLoanNumber(Long.toString(loanNumber));
        loans.setMobileNumber(mobileNumber);
        loans.setLoanType(LoansConstants.HOME_LOAN);
        loans.setTotalLoan(10000000);
        loans.setAmountPaid(9000000);
        loans.setOutstandingAmount(1000000);
        return  loans;
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Optional<Loans> loans=loansRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Loans","mobileNumber",mobileNumber));
        LoansDto loansDto =LoansMapper.mapToLoansDto(new LoansDto(),loans);
        return loansDto;
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Optional<Loans> optionalLoans=loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(()-> new ResourceNotFoundException("Loans","loanNumber", loansDto.getLoanNumber()));
        Loans loans = LoansMapper.mapToLoans(optionalLoans.get(), loansDto);
         loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans =loansRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Loan","mobileNumber",mobileNumber));
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }
}

package org.example.PetStore.service;

import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.PetStore.model.bo.AdoptionAdoptInputBO;
import org.example.PetStore.model.bo.AdoptionAdoptersInputBO;
import org.example.PetStore.model.bo.AdoptionLoginInputBO;
import org.example.PetStore.model.bo.AdoptionRegisterInputBO;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
public class AdoptionService {
  public static final String ABI = org.example.PetStore.utils.IOUtil.readResourceAsString("abi/Adoption.abi");

  public static final String BINARY = org.example.PetStore.utils.IOUtil.readResourceAsString("bin/ecc/Adoption.bin");

  public static final String SM_BINARY = org.example.PetStore.utils.IOUtil.readResourceAsString("bin/sm/Adoption.bin");

  @Value("${system.contract.adoptionAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public CallResponse login(AdoptionLoginInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "login", input.toArgs());
  }

  public CallResponse getAdopters() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getAdopters", Arrays.asList());
  }

  public TransactionResponse adopt(AdoptionAdoptInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "adopt", input.toArgs());
  }

  public TransactionResponse register(AdoptionRegisterInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "register", input.toArgs());
  }

  public CallResponse adopters(AdoptionAdoptersInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "adopters", input.toArgs());
  }
}

package org.example.PetStore.service;

import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.PetStore.config.SystemConfig;
import org.fisco.bcos.sdk.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@Slf4j
public class ServiceManager {
  @Autowired
  private SystemConfig config;

  @Autowired
  private Client client;

  List<String> hexPrivateKeyList;

  @PostConstruct
  public void init() {
    hexPrivateKeyList = Arrays.asList(this.config.getHexPrivateKey().split(","));
  }

  /**
   * @notice: must use @Qualifier("AdoptionService") with @Autowired to get this Bean
   */
  @Bean("AdoptionService")
  public Map<String, AdoptionService> initAdoptionServiceManager() throws Exception {
    Map<String, AdoptionService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	AdoptionService adoptionService = new AdoptionService();
    	adoptionService.setAddress(this.config.getContract().getAdoptionAddress());
    	adoptionService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor = 
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	adoptionService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, adoptionService);
    }
    log.info("++++++++AdoptionService map:{}", serviceMap);
    return serviceMap;
  }
}

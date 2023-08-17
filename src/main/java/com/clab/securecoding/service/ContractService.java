package com.clab.securecoding.service;

import com.clab.securecoding.mapper.ContractMapper;
import com.clab.securecoding.repository.AnimalRepository;
import com.clab.securecoding.repository.ContractRepository;
import com.clab.securecoding.type.dto.ContractRequestDto;
import com.clab.securecoding.type.entity.Contract;
import com.clab.securecoding.type.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;

    private final AnimalRepository animalRepository;

    private final ContractMapper contractMapper;

    private final UserService userService;

    @Transactional
    public void createContract(ContractRequestDto contractRequestDto) {
        Contract contract = contractMapper.mapDtoToEntity(contractRequestDto);
        contract.setUser(userService.getCurrentUser());
        animalRepository.save(contract.getAnimal());
        contractRepository.save(contract);
    }

    public Contract getMyContract() {
        User user = userService.getCurrentUser();
        List<Contract> contracts = contractRepository.findByUserOrderByCreatedAtDesc(user);
        if (contracts.size() > 0) {
            return contracts.get(0);
        }
        return null;
    }
}

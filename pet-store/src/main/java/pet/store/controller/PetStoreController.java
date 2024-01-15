package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	@Autowired
	private PetStoreService petStoreService;
	
	@PostMapping("/pet_store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData savePetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Pet store {} created.", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PutMapping("/pet_store/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(petStoreId);
		log.info("Info for pet store {} updated.", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PostMapping("/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee saveEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee petStoreEmployee) {
		log.info("Pet store employee {} created.", petStoreEmployee);
		return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
	}
	
	@PostMapping("/{petStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreCustomer saveCustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer petStoreCustomer) {
		log.info("Pet store customer {} created.", petStoreCustomer);
		return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
	}
	
	@GetMapping
	public List<PetStoreData> retrieveAllPetStores() {
		return petStoreService.retrieveAllPetStores();
	}
	
	@GetMapping("pet_store/{petStoreId}")
	public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
		return petStoreService.retrievePetStoreById(petStoreId);
	}
	
	@DeleteMapping("/pet_store/{petStoreId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
		log.info("Pet store {} deleted.", petStoreId);
		petStoreService.deletePetStoreById(petStoreId);
		return Map.of("message","Pet store " + petStoreId + " has been deleted.");
	}
}

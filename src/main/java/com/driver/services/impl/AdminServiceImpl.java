package com.driver.services.impl;

import com.driver.model.Admin;
import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;

    @Override
    public Admin register(String username, String password) {
        Admin admin = new Admin();
        admin.setPassword(password);
        admin.setUsername(username);
        adminRepository1.save(admin);

        return admin;
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) {
        Admin admin = adminRepository1.findById(adminId).get();

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setAdmin(admin);
        serviceProvider.setName(providerName);

        admin.getServiceProviders().add(serviceProvider);


//        List<ServiceProvider> serviceProviderList = admin.getServiceProviderList();
//        List<ServiceProvider> serviceProviders = new ArrayList<>();
//
//        for (ServiceProvider serviceProvider:serviceProviderList){
//            ServiceProvider serviceProvider1 = new ServiceProvider();
//            serviceProvider1.setName(providerName);
//
//            serviceProviders.add(serviceProvider);
//        }
//        admin.setServiceProviderList(serviceProviders);

        adminRepository1.save(admin);

        return admin;
    }

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception{
        if(!caseIgnoreCheckAndEnumCheck(countryName.toUpperCase())){
            throw new Exception("Country not found");
        }
        ServiceProvider serviceProvider = serviceProviderRepository1.findById(serviceProviderId).get();

        List<Country> countryList = serviceProvider.getCountryList();
        List<Country>  countries = new ArrayList<>();

        for (Country country:countryList){
            Country country1 = new Country();
             country1.setCountryName(CountryName.valueOf(countryName.toUpperCase()));
            countries.add(country1);
        }
        serviceProvider.setCountryList(countryList);
        serviceProviderRepository1.save(serviceProvider);

        return serviceProvider;
    }
    public boolean caseIgnoreCheckAndEnumCheck(String countryName){
        for (CountryName countryName1 : CountryName.values()) {
            if (countryName1.name().equalsIgnoreCase(countryName)) {
                return true;
            }
        }
        return false;
    }
}

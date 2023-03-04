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
//        if(!caseIgnoreCheckAndEnumCheck(countryName.toUpperCase())){
//            throw new Exception("Country not found");
//        }
//        ServiceProvider serviceProvider = serviceProviderRepository1.findById(serviceProviderId).get();
//
//        List<Country> countryList = serviceProvider.getCountryList();
//        List<Country>  countries = new ArrayList<>();
//
//        for (Country country:countryList){
//            Country country1 = new Country();
//             country1.setCountryName(CountryName.valueOf(countryName.toUpperCase()));
//            countries.add(country1);
//        }
//        serviceProvider.setCountryList(countryList);
//        serviceProviderRepository1.save(serviceProvider);
//
//        return serviceProvider;
        if(countryName.equalsIgnoreCase("ind") || countryName.equalsIgnoreCase("usa") || countryName.equalsIgnoreCase("aus")||countryName.equalsIgnoreCase("jpn")||countryName.equalsIgnoreCase("chi")){

            Country country = new Country();
            ServiceProvider serviceProvider = serviceProviderRepository1.findById(serviceProviderId).get();

            if (countryName.equalsIgnoreCase("ind")){
                country.setCountryName(CountryName.IND);
                country.setCode(CountryName.IND.toCode());
            }
            if (countryName.equalsIgnoreCase("usa")){
                country.setCountryName(CountryName.USA);
                country.setCode(CountryName.USA.toCode());
            }
            if (countryName.equalsIgnoreCase("aus")){
                country.setCountryName(CountryName.AUS);
                country.setCode(CountryName.AUS.toCode());
            }
            if (countryName.equalsIgnoreCase("jpn")){
                country.setCountryName(CountryName.JPN);
                country.setCode(CountryName.JPN.toCode());
            }
            if (countryName.equalsIgnoreCase("chi")){
                country.setCountryName(CountryName.CHI);
                country.setCode(CountryName.CHI.toCode());
            }
            country.setServiceProvider(serviceProvider);
            serviceProvider.getCountryList().add(country);
            serviceProviderRepository1.save(serviceProvider);

            return serviceProvider;
        }
        else
            throw new Exception("Country not found");
    }
}

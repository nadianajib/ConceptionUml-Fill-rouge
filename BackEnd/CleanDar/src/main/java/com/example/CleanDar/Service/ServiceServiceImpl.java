package com.example.CleanDar.Service;

import com.example.CleanDar.Dao.ServiceRepository;
import com.example.CleanDar.model.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    @Override
    public Service addService(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Service updateService(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public Service getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }
}

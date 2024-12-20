package org.example.finalproject.admin.mappers.impls;

import org.example.finalproject.admin.dtos.admin.AdminDto;
import org.example.finalproject.admin.dtos.admin.AdminRegistrationRequestDto;
import org.example.finalproject.admin.mappers.AdminMapper;
import org.example.finalproject.admin.models.admin.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapperImpl implements AdminMapper {
    @Override
    public Admin toEntity(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setId(adminDto.getId());
        admin.setName(adminDto.getName());
        admin.setSurname(adminDto.getSurname());
        admin.setPhoneNumber(adminDto.getPhoneNumber());
        admin.setEmail(adminDto.getEmail());
        admin.setAge(adminDto.getAge());
        admin.setDateOfBirth(adminDto.getDateOfBirth());
        admin.setGender(adminDto.getGender());
        admin.setAddress(adminDto.getAddress());
        admin.setRole(adminDto.getRole());
        admin.setPhoto(adminDto.getPhoto());
        return admin;
    }

    @Override
    public AdminDto toDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(admin.getId());
        adminDto.setName(admin.getName());
        adminDto.setSurname(admin.getSurname());
        adminDto.setPhoneNumber(admin.getPhoneNumber());
        adminDto.setEmail(admin.getEmail());
        adminDto.setAge(admin.getAge());
        adminDto.setDateOfBirth(admin.getDateOfBirth());
        adminDto.setGender(admin.getGender());
        adminDto.setAddress(admin.getAddress());
        adminDto.setRole(admin.getRole());
        adminDto.setPhoto(admin.getPhoto());
        return adminDto;
    }


    @Override
    public Admin fromAdminRegistrationDto(AdminRegistrationRequestDto adminRegDto) {
        Admin admin = new Admin();
        admin.setName(adminRegDto.getName());
        admin.setSurname(adminRegDto.getSurname());
        admin.setDateOfBirth(adminRegDto.getDateOfBirth());
        admin.setPhoneNumber(adminRegDto.getPhoneNumber());
        admin.setGender(adminRegDto.getGender());
        admin.setCountry(adminRegDto.getCountry());
        admin.setAddress(adminRegDto.getAddress());
        admin.setPostcode(adminRegDto.getPostcode());
        admin.setCity(adminRegDto.getCity());
        admin.setPassword(adminRegDto.getPassword());

        return admin;
    }
}

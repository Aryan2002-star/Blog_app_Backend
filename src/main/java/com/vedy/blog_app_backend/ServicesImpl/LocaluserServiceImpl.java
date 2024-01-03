package com.vedy.blog_app_backend.ServicesImpl;

import com.vedy.blog_app_backend.Dto.LocalUserDto;
import com.vedy.blog_app_backend.Exception.ResourceNotFoundException;
import com.vedy.blog_app_backend.Model.LocalUser;
import com.vedy.blog_app_backend.Repository.LocaluserRepo;
import com.vedy.blog_app_backend.Services.LocalUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class LocaluserServiceImpl implements LocalUserService {



    @Autowired
    private LocaluserRepo localuserRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public LocalUserDto createUser(LocalUserDto localUserDto) {
        LocalUser user = this.dto_to_user(localUserDto);

        //Here implement the business Logic


        //End of Business Logic
        LocalUser saveduser =  localuserRepo.save(user);

        return user_to_dto(saveduser);
    }



    @Override
    public LocalUserDto updatUser(LocalUserDto localUserDto, Integer id) {
        LocalUser localUser = localuserRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","Id",id));


        localUser.setName(localUserDto.getName());
        localUser.setEmail(localUserDto.getEmail());
        localUser.setPassword(localUserDto.getPassword());
        localUser.setAbout(localUserDto.getAbout());

        localuserRepo.save(localUser);

        return user_to_dto(localUser);

    }


    @Override
    public LocalUserDto getUserById(Integer id) {
        LocalUser localUser  = localuserRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","Id",id));

        return user_to_dto(localUser);
    }

    @Override
    public List<LocalUserDto> getAllUsers() {

        List<LocalUser> localUsers = localuserRepo.findAll();

        return localUsers.stream().map(this::user_to_dto).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Integer id) {
        LocalUser localUser = localuserRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","Id",id));
        localuserRepo.delete(localUser);
    }



    //Convert USer-DTO to user and vice versa as it can be saved in DB
    //Basically this is done as Service layer is used to implement Business Logic
     LocalUser dto_to_user(LocalUserDto localUserDto){

//        LocalUser localUser = new LocalUser();
//        localUser.setId(localUserDto.getId());
//        localUser.setName(localUserDto.getName());
//        localUser.setEmail(localUserDto.getEmail());
//        localUser.setAbout(localUserDto.getAbout());
//        localUser.setPassword(localUserDto.getPassword());


//        Here we can use model mapper(down)
        LocalUser localUser = this.modelMapper.map(localUserDto,LocalUser.class);

        return localUser;

    }

     LocalUserDto user_to_dto(LocalUser localUser){

//        LocalUserDto localUserDto = new LocalUserDto();
////        localUserDto.setId(localUser.getId());
//        localUserDto.setName(localUser.getName());
//        localUserDto.setEmail(localUser.getEmail());
//        localUserDto.setAbout(localUser.getAbout());
//        localUserDto.setPassword(localUser.getPassword());

//         The above can be used to map one object to another in one Line(UP)

         LocalUserDto localUserDto = this.modelMapper.map(localUser,LocalUserDto.class);

        return localUserDto;

     }






}

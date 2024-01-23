package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.dto.accessories.AccessoryDTO;
import com.rodrigo.flexmobilidade.dto.additonalutility.AdditionalUtilityDTO;
import com.rodrigo.flexmobilidade.dto.reserva.ReservaRequestDTO;
import com.rodrigo.flexmobilidade.dto.reserva.ReservaResponseDTO;
import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import com.rodrigo.flexmobilidade.model.additionalutility.AdditionalUtility;
import com.rodrigo.flexmobilidade.model.cars.Cars;
import com.rodrigo.flexmobilidade.model.categories.Category;
import com.rodrigo.flexmobilidade.model.protections.Protection;
import com.rodrigo.flexmobilidade.model.reserva.PersonalData;
import com.rodrigo.flexmobilidade.model.reserva.Reserva;
import com.rodrigo.flexmobilidade.repositories.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ReservaServiceTest {

    public static final int ID = 1;
    public static final int MONTH = 1;
    public static final int YEAR = 2024;
    public static final int DAY = 25;
    public static final int HOUR = 16;
    public static final int MINUTE = 54;
    public static final LocalDateTime FINISH = LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE);
    public static final String LOCATION = "test";
    public static final LocalDateTime NOW = LocalDateTime.now();

    public static final String NAME = "Test";
    public static final String CPF = "99999999999";
    public static final String EMAIL = "test@email.com";
    public static final String TELEFONE = "9999999999";
    public static final PersonalData PERSONAL_DATA = new PersonalData(NAME, CPF, EMAIL, TELEFONE);
    public static final String BENEFITS = "t";
    public static final double VALUE = 1.0;
    public static final Accessory ACCESSORY = new Accessory(ID, NAME, VALUE);
    public static final Protection PROTECTION = new Protection(ID, NAME, BENEFITS, VALUE);
    public static final List<Accessory> ACCESSORIES = new ArrayList<>();
    public static final List<AdditionalUtility> ADDITIONAL_UTILITIES = new ArrayList<>();
    public static final List<AccessoryDTO> ACCESSORY_DTO_LIST = new ArrayList<>();
    public static final List<AdditionalUtilityDTO> ADDITIONAL_UTILITY_DTO_LIST = new ArrayList<>();
    public static final List<Cars> CARS_LIST = new ArrayList<>();
    public static final Category CATEGORY = new Category(ID, NAME, CARS_LIST);
    public static final int QUANTITY = 1;
    public static final AdditionalUtility ADDITIONAL_UTILITY = new AdditionalUtility(ID, NAME, VALUE, QUANTITY);
    public static final int INDEX = 0;
    public static ReservaResponseDTO RESERVA_RESPONSE_DTO = new ReservaResponseDTO(ID, LOCATION, NOW, FINISH, PERSONAL_DATA, CATEGORY, PROTECTION, ACCESSORIES, ADDITIONAL_UTILITIES);
    public static Reserva RESERVA = new Reserva(ID, LOCATION, NOW, FINISH, PERSONAL_DATA, CATEGORY, PROTECTION, ACCESSORIES, ADDITIONAL_UTILITIES);
    public static final int ID_CATEGORY = QUANTITY;
    public static final Integer ID_PROTECTION = QUANTITY;
    public static ReservaRequestDTO RESERVA_REQUEST_DTO = new ReservaRequestDTO(LOCATION, NOW, FINISH, PERSONAL_DATA, ID_CATEGORY, ID_PROTECTION, ACCESSORY_DTO_LIST, ADDITIONAL_UTILITY_DTO_LIST);
    @InjectMocks
    private ReservaService service;
    @Mock
    private  ReservaRepository reservaRepository;
    @Mock
    private  ProtectionService protectionService;
    @Mock
    private  AccessoryService accessoryService;
    @Mock
    private  AdditionalUtilityService additionalUtilityService;
    @Mock
    private  CategoryService categoryService;
    @Mock
    private  ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startReserva();
    }

    @Test
    void whenSaveThenReturnSuccess() {
        when(categoryService.findById(anyInt())).thenReturn(CATEGORY);
        when(protectionService.findById(anyInt())).thenReturn(PROTECTION);
        when(accessoryService.findById(anyInt())).thenReturn(ACCESSORY);
        when(additionalUtilityService.findById(anyInt())).thenReturn(ADDITIONAL_UTILITY);
        when(modelMapper.map(any(), any())).thenReturn(RESERVA_RESPONSE_DTO);
        when(reservaRepository.save(any())).thenReturn(RESERVA);

        ReservaResponseDTO responseDTO = service.save(RESERVA_REQUEST_DTO);

        assertNotNull(responseDTO);
        assertEquals(LOCATION, responseDTO.getLocation());
        assertEquals(NOW, responseDTO.getInicial());
        assertEquals(FINISH, responseDTO.getFinish());
        assertEquals(PERSONAL_DATA, responseDTO.getPersonalData());
        assertEquals(CATEGORY, responseDTO.getCategory());
        assertEquals(PROTECTION, responseDTO.getProtection());
        assertEquals(ACCESSORIES, responseDTO.getAccessories());
        assertEquals(ADDITIONAL_UTILITIES, responseDTO.getAdditionalUtilities());

        verify(categoryService).findById(eq(ID));
        verify(protectionService).findById(eq(ID));
        verify(reservaRepository).save(any());

        verifyNoMoreInteractions(reservaRepository);
    }

    @Test
    void whenFindAllThenReturnSuccess() {
        when(reservaRepository.findAll()).thenReturn(List.of(RESERVA));

        List<ReservaResponseDTO> responseDTO = service.findAll();

        assertNotNull(responseDTO);
        assertEquals(ID, responseDTO.get(INDEX).getId());
        assertEquals(LOCATION, responseDTO.get(INDEX).getLocation());
        assertEquals(NOW, responseDTO.get(INDEX).getInicial());
        assertEquals(FINISH, responseDTO.get(INDEX).getFinish());
        assertEquals(PERSONAL_DATA, responseDTO.get(INDEX).getPersonalData());
        assertEquals(CATEGORY, responseDTO.get(INDEX).getCategory());
        assertEquals(PROTECTION, responseDTO.get(INDEX).getProtection());
        assertEquals(ACCESSORIES, responseDTO.get(INDEX).getAccessories());
        assertEquals(ADDITIONAL_UTILITIES, responseDTO.get(INDEX).getAdditionalUtilities());

        verify(reservaRepository).findAll();

        verifyNoMoreInteractions(reservaRepository);
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(reservaRepository.findById(eq(ID))).thenReturn(Optional.ofNullable(RESERVA));

        Optional<ReservaResponseDTO> responseDTO= service.findById(ID);

        assertNotNull(responseDTO);
        assertEquals(LOCATION, responseDTO.get().getLocation());
        assertEquals(NOW, responseDTO.get().getInicial());
        assertEquals(FINISH, responseDTO.get().getFinish());
        assertEquals(PERSONAL_DATA, responseDTO.get().getPersonalData());
        assertEquals(CATEGORY, responseDTO.get().getCategory());
        assertEquals(PROTECTION, responseDTO.get().getProtection());
        assertEquals(ACCESSORIES, responseDTO.get().getAccessories());
        assertEquals(ADDITIONAL_UTILITIES, responseDTO.get().getAdditionalUtilities());

        verify(reservaRepository).findById(ID);

        verifyNoMoreInteractions(reservaRepository);
    }

    @Test
    void deleteWithSuccess() {
        when(reservaRepository.findById(eq(ID))).thenReturn(Optional.ofNullable(RESERVA));
        doNothing().when(reservaRepository).deleteById(eq(ID));

        service.delete(ID);

        verify(reservaRepository).deleteById(ID);
    }

    @Test
    void deleteWithNotSuccess() {
        when(reservaRepository.findById(eq(ID))).thenReturn(Optional.empty());

        try {
            service.delete(ID);
            fail();
        }catch (Exception e){
            assertEquals(NoSuchElementException.class, e.getClass());
            assertEquals("Reserva with ID:" + ID + " not found", e.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(reservaRepository.findById(eq(ID))).thenReturn(Optional.ofNullable(RESERVA));
        when(categoryService.findById(anyInt())).thenReturn(CATEGORY);
        when(protectionService.findById(anyInt())).thenReturn(PROTECTION);
        when(accessoryService.findById(anyInt())).thenReturn(ACCESSORY);
        when(additionalUtilityService.findById(anyInt())).thenReturn(ADDITIONAL_UTILITY);
        when(modelMapper.map(any(), any())).thenReturn(RESERVA_RESPONSE_DTO);
        when(reservaRepository.save(any())).thenReturn(RESERVA);

        ReservaResponseDTO responseDTO = service.update(RESERVA_REQUEST_DTO, ID);

        assertNotNull(responseDTO);
        assertEquals(LOCATION, responseDTO.getLocation());
        assertEquals(NOW, responseDTO.getInicial());
        assertEquals(FINISH, responseDTO.getFinish());
        assertEquals(PERSONAL_DATA, responseDTO.getPersonalData());
        assertEquals(CATEGORY, responseDTO.getCategory());
        assertEquals(PROTECTION, responseDTO.getProtection());
        assertEquals(ACCESSORIES, responseDTO.getAccessories());
        assertEquals(ADDITIONAL_UTILITIES, responseDTO.getAdditionalUtilities());

        verify(categoryService).findById(eq(ID));
        verify(protectionService).findById(eq(ID));
        verify(reservaRepository).save(any());
    }

    @Test
    void whenUpdateThenReturnNoSuchElementException() {
        when(reservaRepository.findById(eq(ID))).thenReturn (Optional.empty());
        try {
            service.update(RESERVA_REQUEST_DTO, ID);
            fail();
        }catch (Exception e){
            assertEquals(NoSuchElementException.class, e.getClass());
            assertEquals("Reserva with ID:" + ID + " not found", e.getMessage());
        }
    }

    private void startReserva(){
        RESERVA = new Reserva(ID, LOCATION, NOW, FINISH, PERSONAL_DATA, CATEGORY, PROTECTION, ACCESSORIES, ADDITIONAL_UTILITIES);
        RESERVA_RESPONSE_DTO = new ReservaResponseDTO(ID, LOCATION, NOW, FINISH, PERSONAL_DATA, CATEGORY, PROTECTION, ACCESSORIES, ADDITIONAL_UTILITIES);
        RESERVA_REQUEST_DTO = new ReservaRequestDTO(LOCATION, NOW, FINISH, PERSONAL_DATA, ID_CATEGORY, ID_PROTECTION, ACCESSORY_DTO_LIST, ADDITIONAL_UTILITY_DTO_LIST);
    }
}
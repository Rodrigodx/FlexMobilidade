package com.rodrigo.flexmobilidade.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import com.rodrigo.flexmobilidade.services.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ReservaControllerTest {
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
    public static final Protection PROTECTION = new Protection(ID, NAME, BENEFITS, VALUE);
    public static final List<Accessory> ACCESSORIES = new ArrayList<>();
    public static final List<AdditionalUtility> ADDITIONAL_UTILITIES = new ArrayList<>();
    public static final List<AccessoryDTO> ACCESSORY_DTO_LIST = new ArrayList<>();
    public static final List<AdditionalUtilityDTO> ADDITIONAL_UTILITY_DTO_LIST = new ArrayList<>();
    public static final List<Cars> CARS_LIST = new ArrayList<>();
    public static final Category CATEGORY = new Category(ID, NAME, CARS_LIST);
    public static final int QUANTITY = 1;
    public static final int INDEX = 0;
    public static ReservaResponseDTO RESERVA_RESPONSE_DTO = new ReservaResponseDTO(ID, LOCATION, NOW, FINISH, PERSONAL_DATA, CATEGORY, PROTECTION, ACCESSORIES, ADDITIONAL_UTILITIES);
    public static Reserva RESERVA = new Reserva(ID, LOCATION, NOW, FINISH, PERSONAL_DATA, CATEGORY, PROTECTION, ACCESSORIES, ADDITIONAL_UTILITIES);
    public static final int ID_CATEGORY = QUANTITY;
    public static final Integer ID_PROTECTION = QUANTITY;
    public static ReservaRequestDTO RESERVA_REQUEST_DTO = new ReservaRequestDTO(LOCATION, NOW, FINISH, PERSONAL_DATA, ID_CATEGORY, ID_PROTECTION, ACCESSORY_DTO_LIST, ADDITIONAL_UTILITY_DTO_LIST);
    public MockMvc mockMvc;
    public String url = "/api/reserva";
    public String urlId = "/api/reserva/{id}";
    public String json;
    private ObjectMapper objectMapper = new ObjectMapper();
    @InjectMocks
    private ReservaController controller;
    @Mock
    private ReservaService service;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
        objectMapper.registerModule(new JavaTimeModule());
        json = objectMapper.writeValueAsString(RESERVA_REQUEST_DTO);
        startReserva();
    }

    @Test
    void whenSaveThenReturnSuccess()throws Exception{
        when(service.save(any())).thenReturn(RESERVA_RESPONSE_DTO);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        ResponseEntity<ReservaResponseDTO> responseDTO = controller.save(RESERVA_REQUEST_DTO);

        assertNotNull(responseDTO);
        assertEquals(ID, responseDTO.getBody().getId());
        assertEquals(LOCATION, responseDTO.getBody().getLocation());
        assertEquals(NOW, responseDTO.getBody().getInicial());
        assertEquals(FINISH, responseDTO.getBody().getFinish());
        assertEquals(PERSONAL_DATA, responseDTO.getBody().getPersonalData());
        assertEquals(CATEGORY, responseDTO.getBody().getCategory());
        assertEquals(PROTECTION, responseDTO.getBody().getProtection());
        assertEquals(ACCESSORIES, responseDTO.getBody().getAccessories());
        assertEquals(ADDITIONAL_UTILITIES, responseDTO.getBody().getAdditionalUtilities());
        verifyNoMoreInteractions(service);
    }

    @Test
    void whenFindAllThenReturnSuccess()throws Exception{
        when(service.findAll()).thenReturn(List.of(RESERVA_RESPONSE_DTO));

        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<List<ReservaResponseDTO>> responseDTO = controller.findAll();

        assertNotNull(responseDTO);
        assertEquals(ID, responseDTO.getBody().get(INDEX).getId());
        assertEquals(LOCATION, responseDTO.getBody().get(INDEX).getLocation());
        assertEquals(NOW, responseDTO.getBody().get(INDEX).getInicial());
        assertEquals(FINISH, responseDTO.getBody().get(INDEX).getFinish());
        assertEquals(PERSONAL_DATA, responseDTO.getBody().get(INDEX).getPersonalData());
        assertEquals(CATEGORY, responseDTO.getBody().get(INDEX).getCategory());
        assertEquals(PROTECTION, responseDTO.getBody().get(INDEX).getProtection());
        assertEquals(ACCESSORIES, responseDTO.getBody().get(INDEX).getAccessories());
        assertEquals(ADDITIONAL_UTILITIES, responseDTO.getBody().get(INDEX).getAdditionalUtilities());
        verifyNoMoreInteractions(service);

    }

    @Test
    void whenFindByIdThenReturnSuccess() throws Exception {
        when(service.findById(eq(ID))).thenReturn(Optional.ofNullable(RESERVA_RESPONSE_DTO));

        mockMvc.perform(get(urlId, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<Optional<ReservaResponseDTO>> responseDTO = controller.findById(ID);
        assertNotNull(responseDTO);
        assertEquals(ID, responseDTO.getBody().get().getId());
        assertEquals(LOCATION, responseDTO.getBody().get().getLocation());
        assertEquals(NOW, responseDTO.getBody().get().getInicial());
        assertEquals(FINISH, responseDTO.getBody().get().getFinish());
        assertEquals(PERSONAL_DATA, responseDTO.getBody().get().getPersonalData());
        assertEquals(CATEGORY, responseDTO.getBody().get().getCategory());
        assertEquals(PROTECTION, responseDTO.getBody().get().getProtection());
        assertEquals(ACCESSORIES, responseDTO.getBody().get().getAccessories());
        assertEquals(ADDITIONAL_UTILITIES, responseDTO.getBody().get().getAdditionalUtilities());
        verifyNoMoreInteractions(service);

    }

    @Test
    void deleteWithSuccess() throws Exception {
        doNothing().when(service).delete(ID);

        mockMvc.perform(MockMvcRequestBuilders.delete(urlId, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verifyNoMoreInteractions(service);
    }

    @Test
    void whenUpdateThenReturnSuccess() throws Exception {
        when(service.update(any(), anyInt())).thenReturn(RESERVA_RESPONSE_DTO);

        mockMvc.perform(put(urlId, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<ReservaResponseDTO> responseDTO = controller.update(RESERVA_REQUEST_DTO, ID);

        assertNotNull(responseDTO);
        assertEquals(ID, responseDTO.getBody().getId());
        assertEquals(LOCATION, responseDTO.getBody().getLocation());
        assertEquals(NOW, responseDTO.getBody().getInicial());
        assertEquals(FINISH, responseDTO.getBody().getFinish());
        assertEquals(PERSONAL_DATA, responseDTO.getBody().getPersonalData());
        assertEquals(CATEGORY, responseDTO.getBody().getCategory());
        assertEquals(PROTECTION, responseDTO.getBody().getProtection());
        assertEquals(ACCESSORIES, responseDTO.getBody().getAccessories());
        assertEquals(ADDITIONAL_UTILITIES, responseDTO.getBody().getAdditionalUtilities());
        verifyNoMoreInteractions(service);
    }

    private void startReserva(){
        RESERVA = new Reserva(ID, LOCATION, NOW, FINISH, PERSONAL_DATA, CATEGORY, PROTECTION, ACCESSORIES, ADDITIONAL_UTILITIES);
        RESERVA_RESPONSE_DTO = new ReservaResponseDTO(ID, LOCATION, NOW, FINISH, PERSONAL_DATA, CATEGORY, PROTECTION, ACCESSORIES, ADDITIONAL_UTILITIES);
        RESERVA_REQUEST_DTO = new ReservaRequestDTO(LOCATION, NOW, FINISH, PERSONAL_DATA, ID_CATEGORY, ID_PROTECTION, ACCESSORY_DTO_LIST, ADDITIONAL_UTILITY_DTO_LIST);
    }
}
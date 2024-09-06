package com.ou.services.Impl;

import com.ou.dto.response.ParkingLotResponseDTO;
import com.ou.mappers.ParkingLotMapper;
import com.ou.pojo.ParkingLot;
import com.ou.repositories.ParkingLotRepository;
import com.ou.services.ParkingLotService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trucn
 */
@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    @Override
    public Map<String, Object> getParkingLots(Map<String, String> params) {
        return parkingLotRepository.getParkingLots(params);
    }

    @Override
    public List<ParkingLot> getParkingLotsByName(Map<String, String> params) {
        return this.parkingLotRepository.getParkingLotsByName(params);
    }

    @Override
    public ParkingLot getParkingLotDetail(Integer id) {
        return this.parkingLotRepository.getParkingLotDetail(id);
    }

    @Override
    public ParkingLot updateParkingLotInfo(Integer id, ParkingLot updatedParkingLot) {
        return this.parkingLotRepository.updateParkingLotInfo(id, updatedParkingLot);
    }

    @Override
    public ParkingLot createParkingLot(ParkingLot p) {
        return this.parkingLotRepository.createParkingLot(p);
    }

    @Override
    public void deleteParkingLot(Integer id) {
        this.parkingLotRepository.deleteParkingLot(id);
    }

    @Override
    public ParkingLotResponseDTO getDTOParkingLotDetail(Integer id) {
        ParkingLot parkingLot = parkingLotRepository.getParkingLotDetail(id);
        return parkingLotMapper.toParkingLotReponse(parkingLot);
    }

    @Override
    public Map<String, Object> findParkingLots(String name, String address, boolean sortByPriceAsc) {
        return parkingLotRepository.findParkingLots(name, address, sortByPriceAsc);
    }

}

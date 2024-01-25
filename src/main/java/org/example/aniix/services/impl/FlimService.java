package org.example.aniix.services.impl;

import org.example.aniix.dtos.FlimDTO;
import org.example.aniix.dtos.UploadFlimDTO;
import org.example.aniix.dtos.UploadSeasonDTO;
import org.example.aniix.entities.Flim;
import org.example.aniix.entities.Season;
import org.example.aniix.repositories.ICategoryRepository;
import org.example.aniix.repositories.IFlimRepository;
import org.example.aniix.repositories.ISeasonRepository;
import org.example.aniix.services.IFlimService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class FlimService implements IFlimService {
    @Autowired
    private IFlimRepository flimRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private ISeasonRepository seasonRepository;

    @Override
    public List<FlimDTO> getAll() {
        return flimRepository.findAll()
                .stream()
                .map(flim -> modelMapper.map(flim, FlimDTO.class))
                .toList();
    }

    @Override
    public FlimDTO getById(Long id) {
        return modelMapper
                .map(flimRepository.findById(id).orElseThrow(
                        () -> new NoSuchElementException("Not Found")
                ), FlimDTO.class);
    }

    @Override
    public FlimDTO insert(FlimDTO dto) {
        return modelMapper
                .map(
                        flimRepository.save(modelMapper.map(dto, Flim.class)), FlimDTO.class
                );
    }

    @Override
    public void update(FlimDTO dto) {
        flimRepository.save(modelMapper.map(dto, Flim.class));
    }

    @Override
    public void delete(Long id) {
        flimRepository.deleteById(id);
    }

    @Override
    public List<FlimDTO> getAllById(Set<Long> ids) {
        return null;
    }

    @Override
    public List<FlimDTO> Paging(Pageable pageable) {
        return flimRepository.findAll(pageable).
                getContent()
                .stream()
                .map(flim -> modelMapper.map(flim,FlimDTO.class))
                .toList();
    }

    @Override
    public int getTotalPage(int amount) {
        return flimRepository.findAll(Pageable.ofSize(amount)).getTotalPages();
    }

    @Override
    public List<FlimDTO> getAllByCategoryId(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Not Found Category :(( "))
                .getFlimList()
                .stream()
                .map(flim -> modelMapper.map(flim, FlimDTO.class))
                .toList();
    }
    @Transactional
    @Override
    public UploadFlimDTO upload(UploadFlimDTO uploadFlimDTO) {
        return modelMapper
                .map(
                        flimRepository.save(modelMapper.map(uploadFlimDTO, Flim.class)), UploadFlimDTO.class
                );
    }

    @Override
    public UploadFlimDTO getFlimForUpload(Long id) {
        return modelMapper
                .map(flimRepository.findById(id).orElseThrow(
                        () -> new NoSuchElementException("Not Found")
                ), UploadFlimDTO.class);
    }

    @Override
    public UploadFlimDTO addSeason(UploadSeasonDTO uploadSeasonDTO) {
        return modelMapper
                .map(seasonRepository.save(modelMapper.map(uploadSeasonDTO, Season.class)), UploadFlimDTO.class);
    }

    @Override
    public FlimDTO getBySeasonId(Long id) {
        return modelMapper
                .map(seasonRepository
                                .findById(id).orElseThrow(() -> new NoSuchElementException("Not found this season"))
                                .getFlim()
                        , FlimDTO.class);
    }

    @Override
    public List<FlimDTO> getTop5Newest() {
        return flimRepository.findTop5ByOrderByUploadDateDesc()
                .stream()
                .map(flim -> modelMapper.map(flim, FlimDTO.class))
                .toList();
    }

    @Override
    public List<FlimDTO> getTop10Newest() {
        return flimRepository.findTop10ByOrderByUploadDateDesc()
                .stream()
                .map(flim -> modelMapper.map(flim, FlimDTO.class))
                .toList();
    }

    @Override
    public List<FlimDTO> getAllNewest() {
        return flimRepository.findAllByOrderByUploadDateDesc()
                .stream()
                .map(flim -> modelMapper.map(flim, FlimDTO.class))
                .toList();    }
}

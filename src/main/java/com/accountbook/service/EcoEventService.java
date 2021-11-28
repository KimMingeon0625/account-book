package com.accountbook.service;

import com.accountbook.domain.entity.Category;
import com.accountbook.domain.entity.EcoEvent;
import com.accountbook.domain.repository.category.CategoryRepository;
import com.accountbook.domain.repository.ecoEvent.EcoEventRepository;
import com.accountbook.dto.EcoEvent.EcoEventDto;
import com.accountbook.dto.EcoEvent.EcoEventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EcoEventService {
    private final EcoEventRepository ecoEventRepository;

    private final CategoryRepository categoryRepository;

    //이벤트 전체 조회
    @Transactional(readOnly = true)
    public List<EcoEventDto> getAllEcoEvent(){
        return ecoEventRepository.findAll().stream().map(EcoEventDto::new).collect(Collectors.toList());
    }

    //이벤트 상세 조회
    @Transactional(readOnly = true)
    public EcoEventDto getOneEcoEvent(Long ecoEventSeq){
        return new EcoEventDto(ecoEventRepository.findBySeq(ecoEventSeq).orElseThrow(() -> new NoSuchElementException("해당 금융 이벤트가 존재하지 않습니다.")));
    }

    @Transactional(readOnly = true)
    public List<EcoEventDto> getEcoEventByUser(String userId){
        return ecoEventRepository.findByUserId(userId).stream().map(EcoEventDto::new).collect(Collectors.toList());
    }

    //이벤트 등록
    public void enrollEcoEvents(EcoEventRequest ecoEventRequest){
        Category category = categoryRepository.getCategory(ecoEventRequest.getCategorySeq());
        EcoEvent ecoEvent = EcoEvent.createEcoEvent(ecoEventRequest, category);
        ecoEventRepository.save(ecoEvent);
    }

    //이벤트 수정
    public void updateEcoEvents(EcoEventRequest ecoEventRequest, Long ecoEventSeq){
        Category category = categoryRepository.getCategory(ecoEventRequest.getCategorySeq());
        EcoEvent ecoEvent = ecoEventRepository.findBySeq(ecoEventSeq).orElseThrow(()-> new NoSuchElementException("해당 금융 이벤트가 존재하지 않습니다."));
        ecoEvent.changeEcoEvent(ecoEventRequest,category);
        ecoEventRepository.save(ecoEvent);
    }

    //이벤트 삭제
    public void deleteEcoEvents(Long EventsSeq){
        ecoEventRepository.deleteById(EventsSeq);
    }
}
package com.pluto.service.impl;

import com.pluto.entity.SortieDTO;
import com.pluto.mapper.SortieMapper;
import com.pluto.service.SortieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SortieServiceImpl extends SuperServiceImpl<SortieMapper, SortieDTO> implements SortieService {
}

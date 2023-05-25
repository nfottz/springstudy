package com.gdu.app13.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import com.gdu.app13.domain.BlogDTO;
import com.gdu.app13.domain.SummernoteImageDTO;

@Mapper
public interface BlogMapper {
	public int getBlogCount();
	public List<BlogDTO> getBlogList(Map<String, Object> map);
	public int addBlog(BlogDTO blogDTO); // 삽입할 데이터를 BlogDTO로 만들어서 작업
	public int addSummernoteImage(SummernoteImageDTO summernoteImageDTO);
	public int increaseHit(int blogNo);
	public BlogDTO getBlogByNo(int blogNo);
}

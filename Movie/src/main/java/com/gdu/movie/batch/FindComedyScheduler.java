package com.gdu.movie.batch;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.movie.domain.MovieDTO;
import com.gdu.movie.domain.QueryDTO;
import com.gdu.movie.service.MovieService;

@Component
@EnableScheduling
public class FindComedyScheduler {

  @Autowired
  private MovieService movieService;
  
  @Scheduled(cron="0 0/1 * 1/1 * ?")
  public void execute() {
    QueryDTO queryDTO = new QueryDTO();
    queryDTO.setColumn("GENRE");
    queryDTO.setSearchText("코미디");
    
    Map<String, Object> map = movieService.getMoviesByQuery(queryDTO);
    int status = (int)map.get("status");
    
    PrintWriter writer = null;
    
    try {
      
      if(status == 200) {
        File file = new File("코미디.txt");
        writer = new PrintWriter(file);
        List<MovieDTO> list = (ArrayList<MovieDTO>)map.get("list");
        for(int i = 0; i < list.size(); i++) {
          writer.println("제목 : " + list.get(i).getTitle());
          writer.println("장르 : " + list.get(i).getGenre());
          writer.println("개요 : " + list.get(i).getDescription());
          writer.println("평점 : " + list.get(i).getStar());
        }
        writer.flush();
      }
      
      else if(status == 500) {
        File file = new File("error.txt");
        writer = new PrintWriter(file);
        writer.println(queryDTO.getSearchText() + " 검색 결과가 없습니다.");
        writer.flush();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      writer.close();
    }
  }
  
}

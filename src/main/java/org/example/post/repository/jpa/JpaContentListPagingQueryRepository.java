package org.example.post.repository.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.post.ui.dto.GetContentResponseDto;
import org.example.user.repository.entity.QUserRelationEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaContentListPagingQueryRepository {
  
  private final JPAQueryFactory queryFactory;
  private static final QUserRelationEntity relationEntity = QUserRelationEntity.userRelationEntity;
  
  public List<GetContentResponseDto> getContentResponse(Long userId, Long lastContentId) {
    
    return null;
  }
}
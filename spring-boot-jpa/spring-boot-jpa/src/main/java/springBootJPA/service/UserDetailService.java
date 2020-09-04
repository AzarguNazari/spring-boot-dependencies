package springBootJPA.service;

import springBootJPA.model.UserDetail;
import springBootJPA.param.UserDetailParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDetailService {
    Page<UserDetail> findByCondition(UserDetailParam detailParam, Pageable pageable);
}

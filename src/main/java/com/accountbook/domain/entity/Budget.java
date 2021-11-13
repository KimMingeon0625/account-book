package com.accountbook.domain.entity;

import com.accountbook.domain.enums.PeriodType;
import com.accountbook.dto.Budget.BudgetRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue
    @Column(name = "BUDGET_SEQ")
    private Long seq;

    @OneToOne
    @JoinColumn(name = "USER_CATEGORY_SEQ")
    private UserCategory category;

    @Enumerated(EnumType.STRING)
    private PeriodType periodType;

    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    //생성 메소드
    public static Budget createBudget(BudgetRequest budgetRequest) {
        Budget budget = new Budget();
        budget.category = budgetRequest.getCategory();
        budget.periodType = budgetRequest.getPeriodType();
        budget.amount =  budgetRequest.getAmount();
        budget.setUser(budgetRequest.getUser());
        return budget;
    }

    //연관 관계 메소드
    private void setUser(User user) {
        this.user = user;
        user.getBudgetList().add(this);
    }

    //비즈니스 로직
    public void changeBudget(BudgetRequest budgetRequest){
        this.category = budgetRequest.getCategory();
        this.periodType = budgetRequest.getPeriodType();
        this.amount =  budgetRequest.getAmount();
    }
}

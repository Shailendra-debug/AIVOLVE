package com.synapse.ai.volvo.Service;

import com.synapse.ai.volvo.DTO.FeedbackRequest;
import com.synapse.ai.volvo.DTO.FeedbackResponse;
import com.synapse.ai.volvo.DTO.FeedbackSummaryResponse;
import com.synapse.ai.volvo.Entity.Feedback;
import com.synapse.ai.volvo.Repository.FeedbackRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;


    @Override
    public FeedbackResponse createFeedback(FeedbackRequest request) {

        Feedback feedback = Feedback.builder()
                .name(request.getName())
                .rollNumber(request.getRollNumber())
                .Year(request.getYear())
                .overallExperience(request.getOverallExperience())
                .organizationManagement(request.getOrganizationManagement())
                .activitiesSessions(request.getActivitiesSessions())
                .likedMost(request.getLikedMost())
                .suggestions(request.getSuggestions())
                .build();

        Feedback savedFeedback = feedbackRepository.save(feedback);

        return mapToResponse(savedFeedback);
    }


    @Override
    @Transactional(readOnly = true)
    public FeedbackResponse getFeedbackById(UUID id) {

        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Feedback not found with id: " + id
                        )
                );

        return mapToResponse(feedback);
    }


    @Override
    @Transactional(readOnly = true)
    public List<FeedbackResponse> getAllFeedbacks() {

        return feedbackRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public FeedbackSummaryResponse getFeedbackSummary() {

        List<Feedback> feedbacks = feedbackRepository.findAll();

        if (feedbacks.isEmpty()) {
            return FeedbackSummaryResponse.builder()
                    .totalFeedbacks(0L)
                    .averageOverallExperience(0.0)
                    .averageOrganizationManagement(0.0)
                    .averageActivitiesSessions(0.0)
                    .build();
        }

        double averageOverallExperience = feedbacks.stream()
                .mapToInt(Feedback::getOverallExperience)
                .average()
                .orElse(0.0);

        double averageOrganizationManagement = feedbacks.stream()
                .mapToInt(Feedback::getOrganizationManagement)
                .average()
                .orElse(0.0);

        double averageActivitiesSessions = feedbacks.stream()
                .mapToInt(Feedback::getActivitiesSessions)
                .average()
                .orElse(0.0);

        return FeedbackSummaryResponse.builder()
                .totalFeedbacks((long) feedbacks.size())
                .averageOverallExperience(round(averageOverallExperience))
                .averageOrganizationManagement(round(averageOrganizationManagement))
                .averageActivitiesSessions(round(averageActivitiesSessions))
                .build();
    }


    @Override
    public void deleteFeedback(UUID id) {

        if (!feedbackRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Feedback not found with id: " + id
            );
        }

        feedbackRepository.deleteById(id);
    }


    private FeedbackResponse mapToResponse(Feedback feedback) {

        return FeedbackResponse.builder()
                .id(feedback.getId())
                .name(feedback.getName())
                .RollNumber(feedback.getRollNumber())
                .Year(feedback.getYear())
                .overallExperience(feedback.getOverallExperience())
                .organizationManagement(
                        feedback.getOrganizationManagement()
                )
                .activitiesSessions(
                        feedback.getActivitiesSessions()
                )
                .likedMost(feedback.getLikedMost())
                .suggestions(feedback.getSuggestions())
                .createdAt(feedback.getCreatedAt())
                .build();
    }


    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
package com.synapse.ai.volvo.Service;


import com.synapse.ai.volvo.DTO.FeedbackRequest;
import com.synapse.ai.volvo.DTO.FeedbackResponse;
import com.synapse.ai.volvo.DTO.FeedbackSummaryResponse;

import java.util.List;
import java.util.UUID;

public interface FeedbackService {

    FeedbackResponse createFeedback(FeedbackRequest request);

    FeedbackResponse getFeedbackById(UUID id);

    List<FeedbackResponse> getAllFeedbacks();

    FeedbackSummaryResponse getFeedbackSummary();

    void deleteFeedback(UUID id);
}

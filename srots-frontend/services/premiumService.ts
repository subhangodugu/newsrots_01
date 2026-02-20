import api from './api';

export interface PremiumSubscriptionRequest {
    months: number;
    utr: string;
}

export interface PremiumResponse {
    message: string;
    expiryDate: string;
}

export const PremiumService = {
    subscribe: async (data: PremiumSubscriptionRequest): Promise<PremiumResponse> => {
        const response = await api.post('/premium/subscribe', data);
        return response.data;
    }
};

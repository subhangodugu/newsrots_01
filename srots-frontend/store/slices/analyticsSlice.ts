
import { createSlice, createAsyncThunk, PayloadAction } from '@reduxjs/toolkit';
import {
  AnalyticsService,
  AnalyticsOverviewResponse,
  SystemAnalyticsResponse
} from '../../services/analyticsService';

interface AnalyticsState {
  overview: AnalyticsOverviewResponse | null;
  system: SystemAnalyticsResponse | null;
  loading: boolean;
  error: string | null;
}

const initialState: AnalyticsState = {
  overview: null,
  system: null,
  loading: false,
  error: null,
};

export const fetchOverview = createAsyncThunk(
  'analytics/fetchOverview',
  async () => {
    return await AnalyticsService.getOverview();
  }
);

export const fetchSystemAnalytics = createAsyncThunk(
  'analytics/fetchSystemAnalytics',
  async () => {
    return await AnalyticsService.getSystemAnalytics();
  }
);

const analyticsSlice = createSlice({
  name: 'analytics',
  initialState,
  reducers: {
    clearAnalytics: () => initialState,
  },
  extraReducers: (builder) => {
    // Overview
    builder.addCase(fetchOverview.pending, (state) => {
      state.loading = true;
      state.error = null;
    });
    builder.addCase(fetchOverview.fulfilled, (state, action: PayloadAction<AnalyticsOverviewResponse>) => {
      state.loading = false;
      state.overview = action.payload;
    });
    builder.addCase(fetchOverview.rejected, (state, action) => {
      state.loading = false;
      state.error = action.error.message || 'Failed to load overview analytics';
    });

    // System
    builder.addCase(fetchSystemAnalytics.pending, (state) => {
      state.loading = true;
      state.error = null;
    });
    builder.addCase(fetchSystemAnalytics.fulfilled, (state, action: PayloadAction<SystemAnalyticsResponse>) => {
      state.loading = false;
      state.system = action.payload;
    });
    builder.addCase(fetchSystemAnalytics.rejected, (state, action) => {
      state.loading = false;
      state.error = action.error.message || 'Failed to load system analytics';
    });
  },
});

export const { clearAnalytics } = analyticsSlice.actions;
export default analyticsSlice.reducer;

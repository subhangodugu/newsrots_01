import React, { useEffect, useState } from 'react';
import { Briefcase, Users, Award, Building, Activity, RefreshCw, AlertCircle } from 'lucide-react';
import { AnalyticsService, AnalyticsOverviewResponse } from '../../../../services/analyticsService';

// Sub-components
import { AnalyticsStatsGrid } from './analytics/AnalyticsStatsGrid';
import { AnalyticsChartsRow } from './analytics/AnalyticsChartsRow';
import { AnalyticsRecentJobs } from './analytics/AnalyticsRecentJobs';
import { AnalyticsJobTypePie } from './analytics/AnalyticsJobTypePie';

const Skeleton: React.FC<{ className?: string }> = ({ className }) => (
    <div className={`animate-pulse bg-gray-200 rounded ${className}`}></div>
);

export const AnalyticsDashboard: React.FC = () => {
    const [data, setData] = useState<AnalyticsOverviewResponse | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const fetchData = async () => {
        setLoading(true);
        setError(null);
        try {
            const res = await AnalyticsService.getOverview();
            setData(res);
        } catch (e: any) {
            console.error("Failed to load analytics", e);
            setError(e.response?.data?.message || e.message || "Failed to sync with analytics database");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchData();
    }, []);

    if (loading) {
        return (
            <div className="space-y-8 p-1">
                <div className="space-y-2">
                    <Skeleton className="h-8 w-64" />
                    <Skeleton className="h-4 w-48" />
                </div>
                <div className="grid grid-cols-1 md:grid-cols-4 gap-6">
                    {[1, 2, 3, 4].map(i => <Skeleton key={i} className="h-32 w-full rounded-2xl" />)}
                </div>
                <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
                    <Skeleton className="h-[400px] w-full rounded-3xl" />
                    <Skeleton className="h-[400px] w-full rounded-3xl" />
                </div>
            </div>
        );
    }

    if (error) {
        return (
            <div className="flex flex-col items-center justify-center p-16 bg-white rounded-[2rem] border border-red-100 shadow-sm">
                <div className="p-5 bg-red-50 rounded-2xl mb-6 shadow-sm">
                    <AlertCircle className="text-red-500" size={36} />
                </div>
                <h3 className="text-2xl font-black text-gray-900 mb-3 tracking-tight">Sync Interrupted</h3>
                <p className="text-gray-500 text-center mb-8 max-w-sm leading-relaxed font-medium">{error}</p>
                <button
                    onClick={fetchData}
                    className="flex items-center gap-3 px-8 py-3 bg-blue-600 text-white rounded-full font-black text-xs uppercase tracking-widest hover:bg-blue-700 transition-all shadow-lg hover:shadow-blue-200 active:scale-95"
                >
                    <RefreshCw size={16} /> Re-Establish Connection
                </button>
            </div>
        );
    }

    if (!data) return null;

    // Fix Mapping in Frontend
    const progressDataMapped =
        data.placementProgress?.map((item: any) => ({
            name: item.label || item.name || item.month,
            placed: item.value || item.placed || item.studentsPlaced || 0
        })) || [];

    const branchMapped =
        data.branchDistribution?.map((b: any) => ({
            name: b.label || b.name || b.branch,
            count: b.value || b.count || b.students || 0
        })) || [];

    const jobTypeMapped =
        data.jobTypes?.map((j: any) => ({
            name: j.label || j.name || j.jobType || "Unknown",
            value: j.value || j.count || 0
        })) || [];

    const stats = data.stats || { placedStudents: 0, totalStudents: 0, activeJobs: 0, companiesVisited: 0 };

    const statItems = [
        { label: 'Success Story', value: (stats.placedStudents || 0).toLocaleString(), change: 'Placed', icon: Award, color: 'text-blue-600', bg: 'bg-blue-50' },
        { label: 'Member Base', value: (stats.totalStudents || 0).toLocaleString(), change: 'Students', icon: Users, color: 'text-indigo-600', bg: 'bg-indigo-50' },
        { label: 'Active Pipeline', value: (data.recentJobs?.length || 0).toLocaleString(), change: 'Roles', icon: Briefcase, color: 'text-emerald-600', bg: 'bg-emerald-50' },
        { label: 'Industry Partners', value: (stats.companiesVisited || 0).toLocaleString(), change: 'Companies', icon: Building, color: 'text-amber-600', bg: 'bg-amber-50' }
    ];

    const COLORS = ['#3b82f6', '#6366f1', '#10b981', '#f59e0b', '#ec4899'];

    return (
        <div className="space-y-10 animate-in fade-in slide-in-from-bottom-6 duration-700">
            <div className="flex flex-col md:flex-row justify-between items-start md:items-center gap-6 px-1">
                <div>
                    <div className="flex items-center gap-3 mb-2">
                        <div className="w-2 h-8 bg-blue-600 rounded-full"></div>
                        <h2 className="text-4xl font-black text-gray-900 tracking-tighter">Placement Intelligence</h2>
                    </div>
                    <p className="text-gray-500 font-bold uppercase tracking-[0.15em] text-[10px] ml-5">Proprietary Recruitment Insights & Analytics</p>
                </div>
                <div className="flex gap-3">
                    <span className="px-4 py-2 bg-blue-50 text-blue-700 rounded-2xl text-[10px] font-black flex items-center gap-2 uppercase tracking-widest border border-blue-100/50 shadow-sm">
                        <Activity size={14} className="animate-pulse" /> Live Stream
                    </span>
                </div>
            </div>

            {/* Stats Grid */}
            <AnalyticsStatsGrid stats={statItems} />

            {/* Charts Row 1 */}
            <AnalyticsChartsRow
                branchData={branchMapped}
                progressData={progressDataMapped}
            />

            {/* Charts Row 2 */}
            <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
                <AnalyticsRecentJobs jobs={(data.recentJobs || []).map((j: any) => ({
                    ...j,
                    company: j.companyName || j.company,
                }))} />
                <AnalyticsJobTypePie data={jobTypeMapped} colors={COLORS} />
            </div>
        </div>
    );
};

#!/bin/bash
cd ../charts
helm upgrade --install mailhog-chart mailhog
helm upgrade --install pg-admin pgadmin
helm upgrade --install postgres-chart-chart postgres-chart
helm upgrade --install backend-chart studyspot-backend
helm upgrade --install frontend-chart studyspot-frontend
kubectl rollout restart deployment studyspotdeployment
kubectl rollout restart deployment studyspotfrontenddeployment

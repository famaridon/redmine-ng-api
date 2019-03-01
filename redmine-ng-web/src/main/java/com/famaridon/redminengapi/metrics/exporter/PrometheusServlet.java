package com.famaridon.redminengapi.metrics.exporter;

import io.prometheus.client.exporter.MetricsServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/metrics")
public class PrometheusServlet extends MetricsServlet
{
	
}

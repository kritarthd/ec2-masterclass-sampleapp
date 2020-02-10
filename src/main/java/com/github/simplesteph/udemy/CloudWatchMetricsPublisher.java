package com.github.simplesteph.udemy;
//
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.amazonaws.services.cloudwatch.model.PutMetricDataResult;
import com.amazonaws.services.cloudwatch.model.StandardUnit;

public class CloudWatchMetricsPublisher{

    public void publishResponseTime(String apiName, long milliSeconds, AmazonCloudWatch cw){

        Dimension dimension = new Dimension()
                .withName("Api_name")
                .withValue(apiName);

        MetricDatum datum = new MetricDatum()
                .withMetricName("API_Response_Time")
                .withUnit(StandardUnit.Milliseconds)
                .withValue(Double.valueOf(milliSeconds))
                .withDimensions(dimension);

        PutMetricDataRequest request = new PutMetricDataRequest()
                .withNamespace("EC2/APIs")
                .withMetricData(datum);

        PutMetricDataResult response = cw.putMetricData(request);

    }

}
# Detecting and Stopping Intrusions on a Windows Server

To detect and stop a suspected intrusion on a Windows Server, you can take the following steps:

1. **Use Windows Event Viewer**: Utilize the built-in Windows Event Viewer to detect possible intrusions by monitoring system logs and events for any suspicious activities or unauthorized access attempts.
    - **Access Windows Event Viewer**: Open the Windows Event Viewer tool on the server to monitor system logs and events for any suspicious activities or unauthorized access attempts.
    - **Monitor System Logs**: Regularly review system logs, focusing on areas like failed logon attempts, unusual account activities, or changes in system configurations that could indicate a security breach.
    - **Set Up Rules and Alerts**: Configure rules within the Event Viewer to create alerts for specific events that may indicate intrusion attempts, such as repeated failed login attempts or unauthorized access to critical files.
        - **Access Windows Event Viewer**: Open the Windows Event Viewer on the server to manage and configure event logs.
        - **Create Custom Views**: Navigate to the "Custom Views" section in the Event Viewer and select "Create Custom View" to define specific criteria for events that may indicate intrusion attempts.
        - **Define Filter Criteria**: Set filter criteria such as failed logon attempts, unauthorized access to critical files, or suspicious configuration changes to create targeted alerts.
        - **Configure Alerts**: Specify the actions to be taken when the defined criteria are met, such as sending email notifications, displaying a message, or running a script to respond to potential intrusion attempts.
        - **Test and Refine**: Test the configured rules and alerts to ensure they trigger appropriately for suspicious events. Refine the rules based on feedback and ongoing monitoring.

2. **Implement Windows Event Forwarding**: Set up Windows Event Forwarding to collect and centralize events from devices in your organization, aiding in intrusion detection by analyzing events during normal operations and potential security incidents.
    - **Access Windows Event Forwarding**: Open an elevated command prompt and type `wecutil qc` to configure Windows Event Forwarding.
    - **Create a Subscription**: In the Event Viewer, right-click on "Subscriptions" and select "Create Subscription" to define the parameters for event collection.
    - **Define Event Collection Criteria**: Specify the events to be collected, such as failed logon attempts, suspicious configuration changes, or other activities that may indicate intrusion attempts.
    - **Configure Event Forwarding Servers**: Set up one or more Windows servers to forward event logs to a centralized collector to streamline event analysis and intrusion detection.
    - **Monitor and Analyze Events**: Regularly monitor and analyze the collected events using tools like Advanced Threat Analytics to detect anomalies and potential security incidents.

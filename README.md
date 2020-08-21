# Apigee Edge Token Publisher Demo Plugin

[![Quality](https://img.shields.io/badge/quality-test-yellow)](https://curity.io/resources/code-examples/status/)
[![Availability](https://img.shields.io/badge/availability-binary-blue)](https://curity.io/resources/code-examples/status/)

This is an example event listener SDK Plugin for the Curity Identity Server. The plugin registers an event listener 
listening for issued access token events, and forwards them to Apigee Edge. See the tutorial on 
[Integrating Apigee with Curity](https://curity.io/resources/operate/tutorials/integration/integration-apigee/) for the full story on how 
Apigee Edge might be integrated with Curity.

## Building, installation and configuration

To build the plugin, simply download it and run `mvn package`. This creates `target/identityserver.plugins.events.listeners.apigee-edge-token-publisher-1.0.0.jar`. Create a new folder `apigee_edge_token_publisher` in `<idsvr_home>/usr/share/plugins/` then copy the jar to that folder and (re)start the Curity Identity Server. Configure a new event listener (shown here using the Admin UI, but might just as well be configured through the CLI, REST or XML):

![Add new listener](docs/new_listener.png)

Pick a suitable name and then select the "apigee-edge-token-publisher" type:

![Select type](docs/select_type.png)

Configure your listener to point to your Apigee Edge endpoint:

![Configure the listener](docs/configure_listener.png)

Please visit [curity.io](https://curity.io/) for more information about the Curity Identity Server.

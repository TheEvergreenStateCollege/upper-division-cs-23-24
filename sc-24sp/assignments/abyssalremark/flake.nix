{
    #the packages we pull from
    inputs = {
        nixpkgs.url = "nixpkgs/nixos-23.11";
        utils.url = "github:numtide/flake-utils";
        fenix = {
            url = "github:nix-community/fenix";
            inputs.nixpkgs.follows = "nixpkgs";
        };

    };

    #defines a function that ... 
    outputs = {self, nixpkgs, utils, fenix, ...}: 
        utils.lib.eachDefaultSystem ( system:
            let 
                pkgs = import nixpkgs {
                    inherit system; #this makes it so we can make this for anything that can nix
                };
                feRust = with fenix.packages.${system}; combine [
                    stable.toolchain 
                    targets.wasm32-unknown-unknown.stable.rust-std
                ];
            in
                {
                    devShells.default = pkgs.mkShell {
                        packages = with pkgs; [
                            nodejs_21
                            wasm-pack
                            feRust
                            llvmPackages.bintools
                        ];
                        CARGO_TARGET_WASM32_UNKNOWN_UNKNOWN_LINKER = "lld";
                    };
                }
        );

}

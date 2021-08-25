package main

import (
	"fmt"
	"github.com/shirou/gopsutil/v3/process"
	"github.com/spf13/cobra"
	"log"
)

func main() {
	winCmd := winCmd()
	winProcessListByPortCmd := winProcessListByPortCmd()
	winCmd.AddCommand(winProcessListByPortCmd)
	var rootCmd = &cobra.Command{Use: "app"}
	rootCmd.AddCommand(winCmd)
	rootCmd.ExecuteC()
}

func winCmd() *cobra.Command {
	var winCmd = &cobra.Command{
		Use:   "win",
		Short: "windows工具箱",
		Long:  ``,
		Args:  cobra.MinimumNArgs(1),
		Run: func(cmd *cobra.Command, args []string) {
			fmt.Println("win10")
		},
	}
	return winCmd
}

func winProcessListByPortCmd() *cobra.Command {
	var winCmd = &cobra.Command{
		Use:   "list",
		Short: "通过端口号输出进程列表",
		Long:  ``,
		Args:  cobra.MinimumNArgs(1),
		Run: func(cmd *cobra.Command, args []string) {
			ps, err := process.Processes()
			if err != nil {
				log.Fatal(err)
			}
			for _, v := range ps {
				log.Println(v.Name())
			}
		},
	}
	return winCmd
}
